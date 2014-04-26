package bros.sonymobile.ludumdare;
import com.sonyericsson.extras.liveware.extension.util.ExtensionUtils;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.EditText;

public class StartFragment extends Fragment {
	

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_main, container,
				false);
		
		Log.i("LudumDare", "Fragment Inflated");
		
		return rootView;
	}
	
	@Override
	public void onStart() {
		super.onStart();
		Log.i("LudumDare", "OnStart");
//		Respond to clicks on Send-button
		getView().findViewById(R.id.fragment_main_send_button).setOnClickListener(new OnClickListener() {		
		@Override
		public void onClick(View v) {
			
//		Create intent and find EditText-fields
		Intent intent;
		EditText editTextTitle = (EditText) getView().findViewById(R.id.fragment_main_title);
		EditText editTextBody = (EditText) getView().findViewById(R.id.fragment_main_body);
		
//		We intend to start the SonyExtensionService service.
		intent = new Intent(getActivity(), SonyExtensionService.class);
		intent.setAction(SonyExtensionService.INTENT_SEND_NOTIFICATION);
		intent.putExtra(SonyExtensionService.EXTRA_TITLE, editTextTitle.getText().toString());
		intent.putExtra(SonyExtensionService.EXTRA_MESSAGE, editTextBody.getText().toString());
		
		getActivity().startService(intent);
		}
	});
	}
	
}
