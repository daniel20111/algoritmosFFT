package com.vanderbie.heart_rate_monitor;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import android.os.AsyncTask;
/**
 * UDP streamer class
 * 
 * @author Joey van der Bie <joey@vanderbie.net>
 */
public class UDPThread  extends AsyncTask<String, Void, Void> {
		
		String msensordata;

		@Override
		protected Void doInBackground(String... params) {
			byte bytes [] ;
			msensordata = params[0];
			
			try {
				bytes = msensordata.getBytes("UTF-8");
				if (HeartRateMonitor.mPacket == null || HeartRateMonitor.mSocket == null)
					return null ;
				
				HeartRateMonitor.mPacket.setData(bytes);
				HeartRateMonitor.mPacket.setLength(bytes.length);


				HeartRateMonitor.mSocket.send(HeartRateMonitor.mPacket);
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				//Log.e("Error", "SendBlock");
				return null;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				//Log.e("Error", "SendBlock");
				return null;
			}
			return null;
		}
	}