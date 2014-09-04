package org.vocefiscal.activities;

import org.vocefiscal.R;

import com.facebook.*;
import com.facebook.model.*;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class FiscalizacaoConcluidaActivity extends Activity 
{

	public static final String TAB_TO_SELECT = "tab_to_select";
	public static final int FISCALIZAR = 0;
	public static final int CONFERIR = 1;
	
	private String secao;
	private String zonaEleitoral;
	private String municipio;

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fiscalizacao_concluida);
		
		//Start Facebook Login
		Session.openActiveSession(this, true, new Session.StatusCallback() 
		{
			
			@Override
			//callback when session changes state
			public void call(Session session, SessionState state, Exception exception) 
			{
				if(session.isOpened())
				{
					//make request to the /me API
					Request.executeMeRequestAsync(session, new Request.GraphUserCallback()
					{
						//callback after Graph API response with user object
						@Override
						public void onCompleted(GraphUser user, Response response) 
						{
							if(user!= null)
							{
								TextView welcome = (TextView) findViewById(R.id.welcome);
								welcome.setText("Olá" + user.getName() + "!");
								
							}
							
						}
					});
				}
				
			}
		});
	
		
		/*
		 * Captando a missão
		 */
		Intent intent = this.getIntent();
		if(intent!=null)
		{
			Bundle bundle = intent.getExtras();
			if(bundle!=null)
			{
				secao = bundle.getString(InformacoesFiscalizacaoActivity.SECAO);
				zonaEleitoral = bundle.getString(InformacoesFiscalizacaoActivity.ZONA);
				municipio = bundle.getString(InformacoesFiscalizacaoActivity.MUNICIPIO);
			}
		}
		
		TextView secao = (TextView)findViewById(R.id.secaoEleitoral);
		secao.setText(this.secao);
		
		TextView zona = (TextView)findViewById(R.id.zonaEleitoral);
		zona.setText(this.zonaEleitoral);
		
		TextView municipio = (TextView)findViewById(R.id.municipio);
		municipio.setText(this.municipio);
		
	}

	/**
	 * Chamada quando o botão Fiscalizar é clicado
	 * @param view
	 */
	public void fiscalizar(View view) 
	{
		
		Intent intent = new Intent(FiscalizacaoConcluidaActivity.this,HomeActivity.class);
		Bundle bundle = new Bundle();
		bundle.putInt(TAB_TO_SELECT, FISCALIZAR);
		intent.putExtras(bundle);
		startActivity(intent);
		
		finish();
	}

	/**
	 * Chamada quando o botão Conferir é clicado
	 * @param view
	 */
	public void conferir(View view) 
	{
		
		Intent intent = new Intent(FiscalizacaoConcluidaActivity.this,HomeActivity.class);
		Bundle bundle = new Bundle();
		bundle.putInt(TAB_TO_SELECT, CONFERIR);
		intent.putExtras(bundle);
    	startActivity(intent);
    	
    	finish();
	}
	
	/**
	 * Chamada quando o botão proximaSecao é clicado
	 * @param view
	 */
	public void proximaSecao(View view)
	{
		Intent intent = new Intent(FiscalizacaoConcluidaActivity.this,CameraActivity.class);
		startActivity(intent);
		
		finish();
		
	}
	
	public void compartilharFacebook(View view)
	{
		
	}
}