package mediabrowser.apiinteraction.sync.server.mediasync;

import mediabrowser.apiinteraction.IResponse;
import mediabrowser.apiinteraction.Response;
import mediabrowser.apiinteraction.sync.data.ILocalAssetManager;
import mediabrowser.model.apiclient.ServerInfo;
import mediabrowser.model.sync.ItemFileInfo;
import mediabrowser.model.sync.LocalItem;
import mediabrowser.model.sync.SyncDataResponse;

import java.util.ArrayList;

/**
 * Created by Luke on 3/17/2015.
 */
public class SyncDataInnerResponse extends Response<SyncDataResponse>
{
    private ILocalAssetManager localAssetManager;
    private ServerInfo serverInfo;
    private boolean syncUserItemAccess;

    public SyncDataInnerResponse(IResponse innerResponse, ILocalAssetManager localAssetManager, ServerInfo serverInfo, boolean syncUserItemAccess){

        super(innerResponse);
        this.localAssetManager = localAssetManager;
        this.serverInfo = serverInfo;
        this.syncUserItemAccess = syncUserItemAccess;
    }

    @Override
    public void onResponse(SyncDataResponse result) {

        for(String itemIdToRemove : result.getItemIdsToRemove())
        {
            removeItem(serverInfo.getId(), itemIdToRemove);
        }

        if (syncUserItemAccess)
        {
            for (String itemId : result.getItemUserAccess().keySet())
            {
                LocalItem localItem = localAssetManager.getLocalItem(serverInfo.getId(), itemId);

                ArrayList<String> userIdsWithAccess = result.getItemUserAccess().get(itemId);

                if (!userIdsWithAccess.equals(localItem.getUserIdsWithAccess()))
                {
                    localItem.setUserIdsWithAccess(userIdsWithAccess);
                    localAssetManager.addOrUpdate(localItem);
                }
            }
        }

        triggerInnerResponse();
    }

    private void removeItem(String serverId, String itemId)
    {
        LocalItem localItem = localAssetManager.getLocalItem(serverId, itemId);

        if (localItem == null)
        {
            return;
        }

        ArrayList<ItemFileInfo> files = localAssetManager.getFiles(localItem);

        for (ItemFileInfo file : files)
        {
            localAssetManager.deleteFile(file.getPath());
        }

        localAssetManager.delete(localItem);
    }
}
