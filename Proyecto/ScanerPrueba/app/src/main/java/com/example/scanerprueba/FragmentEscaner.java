package com.example.scanerprueba;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class FragmentEscaner extends Fragment {

    private Button boton;
    private IntentResult intentEscanerResult;
    private GestionDBproductos gestionDBproductos;
    private Fragmento fragmento;
    private IntentIntegrator intentIntegrator;
    View view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_escaner , container, false);
        //fragmento
        fragmento = new Fragmento();
        // instanciar base de datos
        gestionDBproductos = new GestionDBproductos();
        // iniciar conexion con la base de datos
        gestionDBproductos.iniciarFirebase(getActivity());
        // instanciar botones
       boton = view.findViewById(R.id.boton);
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                escanear();
            }
        });
        return view;
    }

    // nuevo escaner
    public void escanear(){
        intentIntegrator = new IntentIntegrator(getActivity());
        intentIntegrator.setDesiredBarcodeFormats( IntentIntegrator.ALL_CODE_TYPES);
        intentIntegrator.setOrientationLocked(false);
        intentIntegrator.setCaptureActivity( CaptureActivityPortrait.class);
        intentIntegrator.setBeepEnabled(true);
        intentIntegrator.setCameraId(0);
        intentIntegrator.setBarcodeImageEnabled(false);
        intentIntegrator.setPrompt("Coloque el codigo de barras del producto dentro del rectangulo");
        intentIntegrator.initiateScan();
    }

    // devuelve el resultado de la consulta
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        intentEscanerResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if ( intentEscanerResult != null ){
            if ( intentEscanerResult.getContents() != null ) {
    /*                if (  gestionDBproductos.verificarCodigo(intentEscanerResult) == true ){
                        fragmento.replaceFtoF(getFragment(), R.id.frameFragments, new FragmentCarrito());
                    }
                    else{
                        Toast toast = Toast.makeText(getActivity(), "no", Toast.LENGTH_LONG);
                        toast.show();
                    }*/
                gestionDBproductos.verificarCodigo(intentEscanerResult);
                Toast toast = Toast.makeText(getActivity(), gestionDBproductos.prueba, Toast.LENGTH_LONG);
                toast.show();

            }
        }
    }

    public Fragment getFragment(){
        return this;
    }
}
