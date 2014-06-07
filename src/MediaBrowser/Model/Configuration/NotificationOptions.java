package MediaBrowser.Model.Configuration;

import MediaBrowser.Model.Extensions.*;

public class NotificationOptions
{
	private NotificationOption[] privateOptions;
	public final NotificationOption[] getOptions()
	{
		return privateOptions;
	}
	public final void setOptions(NotificationOption[] value)
	{
		privateOptions = value;
	}

	public NotificationOptions()
	{
		NotificationOption tempVar = new NotificationOption();
		tempVar.setType(NotificationType.TaskFailed.ToString());
		tempVar.setEnabled(true);
		tempVar.setSendToUserMode(SendToUserType.Admins);
		NotificationOption tempVar2 = new NotificationOption();
		tempVar2.setType(NotificationType.ServerRestartRequired.ToString());
		tempVar2.setEnabled(true);
		tempVar2.setSendToUserMode(SendToUserType.Admins);
		NotificationOption tempVar3 = new NotificationOption();
		tempVar3.setType(NotificationType.ApplicationUpdateAvailable.ToString());
		tempVar3.setEnabled(true);
		tempVar3.setSendToUserMode(SendToUserType.Admins);
		NotificationOption tempVar4 = new NotificationOption();
		tempVar4.setType(NotificationType.ApplicationUpdateInstalled.ToString());
		tempVar4.setEnabled(true);
		tempVar4.setSendToUserMode(SendToUserType.Admins);
		NotificationOption tempVar5 = new NotificationOption();
		tempVar5.setType(NotificationType.PluginUpdateInstalled.ToString());
		tempVar5.setEnabled(true);
		tempVar5.setSendToUserMode(SendToUserType.Admins);
		NotificationOption tempVar6 = new NotificationOption();
		tempVar6.setType(NotificationType.PluginUninstalled.ToString());
		tempVar6.setEnabled(true);
		tempVar6.setSendToUserMode(SendToUserType.Admins);
		NotificationOption tempVar7 = new NotificationOption();
		tempVar7.setType(NotificationType.InstallationFailed.ToString());
		tempVar7.setEnabled(true);
		tempVar7.setSendToUserMode(SendToUserType.Admins);
		NotificationOption tempVar8 = new NotificationOption();
		tempVar8.setType(NotificationType.PluginInstalled.ToString());
		tempVar8.setEnabled(true);
		tempVar8.setSendToUserMode(SendToUserType.Admins);
		NotificationOption tempVar9 = new NotificationOption();
		tempVar9.setType(NotificationType.PluginError.ToString());
		tempVar9.setEnabled(true);
		tempVar9.setSendToUserMode(SendToUserType.Admins);
		setOptions(new NotificationOption[] {tempVar, tempVar2, tempVar3, tempVar4, tempVar5, tempVar6, tempVar7, tempVar8, tempVar9});
	}

	public final NotificationOption GetOptions(String type)
	{
		for (NotificationOption i : getOptions())
		{
			if (StringHelper.EqualsIgnoreCase(type, i.getType()))
			{
				return i;
			}
		}
		return null;
	}

	public final boolean IsEnabled(String type)
	{
		NotificationOption opt = GetOptions(type);

		return opt != null && opt.getEnabled();
	}

	public final boolean IsServiceEnabled(String service, String notificationType)
	{
		NotificationOption opt = GetOptions(notificationType);

		return opt == null || !opt.getDisabledServices().Contains(service, StringComparer.OrdinalIgnoreCase);
	}

	public final boolean IsEnabledToMonitorUser(String type, String userId)
	{
		NotificationOption opt = GetOptions(type);

		return opt != null && opt.getEnabled() && !opt.getDisabledMonitorUsers().Contains(userId, StringComparer.OrdinalIgnoreCase);
	}

	public final boolean IsEnabledToSendToUser(String type, String userId, UserConfiguration userConfig)
	{
		NotificationOption opt = GetOptions(type);

		if (opt != null && opt.getEnabled())
		{
			if (opt.getSendToUserMode() == SendToUserType.All)
			{
				return true;
			}

			if (opt.getSendToUserMode() == SendToUserType.Admins && userConfig.getIsAdministrator())
			{
				return true;
			}

			return opt.getSendToUsers().Contains(userId, StringComparer.OrdinalIgnoreCase);
		}

		return false;
	}
}