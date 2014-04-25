package bros.sonymobile.ludumdare;

import android.content.ContentValues;
import android.content.Intent;

import com.sonyericsson.extras.liveware.aef.notification.Notification;
import com.sonyericsson.extras.liveware.extension.util.ExtensionService;
import com.sonyericsson.extras.liveware.extension.util.notification.NotificationUtil;
import com.sonyericsson.extras.liveware.extension.util.registration.RegistrationInformation;

public class SonyExtensionService extends ExtensionService{

	public static final String ID = "bros.sonymobile.ludumdare.ID";
	public static final String KEY = "bros.sonymobile.ludumdare.KEY";
	public static final String NAME = "bros.sonymobile.ludumdare.NAME";
	
	public static final String INTENT_SEND_NOTIFICATION = "bros.sonymobile.ludumdare.SEND_NOTIFICATION";
	public static final String EXTRA_TITLE = "bros.sonymobile.ludumdare.EXTRA_TITLE";
	public static final String EXTRA_MESSAGE = "bros.sonymobile.ludumdare.EXTRA_MESSAGE";
	public static final String EXTRA_ICON = "bros.sonymobile.ludumdare.EXTRA_ICON";
	
	public SonyExtensionService() {
		super(KEY);
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		int val = super.onStartCommand(intent, flags, startId);
		if(intent != null) {
			if(intent.getAction().equals(INTENT_SEND_NOTIFICATION)) {
				String title = intent.getExtras().getString(EXTRA_TITLE);
				String message = intent.getExtras().getString(EXTRA_MESSAGE);
				String icon = intent.getExtras().getString(EXTRA_ICON);
				sendNotifications(title, message, icon);
			}
		}
		return val;
	}
	
	@Override
	protected RegistrationInformation getRegistrationInformation() {
		return new SonyRegistrationInformation(this, ID, KEY, NAME);
	}
	
	@Override
	protected boolean keepRunningWhenConnected() {
		// TODO Auto-generated method stub
		return false;
	}
	
	private void sendNotifications(String title, String message, String icon) {
		long sourceId = NotificationUtil.getSourceId(this, ID);
		
		if(sourceId == NotificationUtil.INVALID_ID) {
			return;
		}
		
		ContentValues data = new ContentValues();
		data.put(Notification.EventColumns.SOURCE_ID, sourceId);
		data.put(Notification.EventColumns.DISPLAY_NAME, title);
		data.put(Notification.EventColumns.MESSAGE, message);
		data.put(Notification.EventColumns.PROFILE_IMAGE_URI, icon);
		data.put(Notification.EventColumns.PUBLISHED_TIME, System.currentTimeMillis());
		data.put(Notification.EventColumns.PERSONAL, 1);
		
		getContentResolver().insert(Notification.Event.URI, data);
		
	}
	
}
