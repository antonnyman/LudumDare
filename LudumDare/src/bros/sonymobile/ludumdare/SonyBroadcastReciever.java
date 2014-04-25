package bros.sonymobile.ludumdare;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class SonyBroadcastReciever extends BroadcastReceiver{

	@Override
	public void onReceive(Context context, Intent intent) {
		intent.setClass(context, SonyExtensionService.class);
		context.startService(intent);
	}

}
