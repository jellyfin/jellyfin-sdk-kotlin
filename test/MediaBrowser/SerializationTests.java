package MediaBrowser;

import MediaBrowser.ApiInteraction.ConsoleLogger;
import MediaBrowser.ApiInteraction.Discovery.ServerLocator;
import MediaBrowser.ApiInteraction.JsonSerializer;
import MediaBrowser.ApiInteraction.Response;
import MediaBrowser.Model.ApiClient.ServerDiscoveryInfo;
import MediaBrowser.Model.Dto.BaseItemDto;
import MediaBrowser.Model.Logging.ILogger;
import MediaBrowser.Model.Querying.ItemsResult;
import MediaBrowser.Model.Querying.QueryResult;
import MediaBrowser.Model.Serialization.IJsonSerializer;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import java.lang.reflect.Type;
import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

@RunWith(RobolectricTestRunner.class)
public class SerializationTests {

    private ILogger logger;
    private IJsonSerializer jsonSerializer = new JsonSerializer();

    @Before
    public void setUp() throws Exception {
        logger = new ConsoleLogger();
    }

    @Test
    public void testSerializeGeneric() throws Exception {

        ItemsResult queryResult = new ItemsResult();

        queryResult.setTotalRecordCount(10);

        BaseItemDto dummy = new BaseItemDto();

        dummy.setType("Video");
        dummy.setMediaType("Video");

        BaseItemDto[] items = new BaseItemDto[]{
                dummy
        };

        queryResult.setItems(items);

        String json = jsonSerializer.SerializeToString(queryResult);

        logger.Info(json);

        queryResult = jsonSerializer.DeserializeFromString(json, new ItemsResult().getClass());

        assertEquals(10, queryResult.getTotalRecordCount());
        assertEquals(1, queryResult.getItems().length);

        dummy = queryResult.getItems()[0];

        assertEquals("Video", dummy.getType());
        assertEquals("Video", dummy.getMediaType());
    }
}