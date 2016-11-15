package br.com.livroandroid.carrosfinal.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import br.com.livroandroid.carrosfinal.Activities.CarroActivity;
import br.com.livroandroid.carrosfinal.CarrosApplication;
import br.com.livroandroid.carrosfinal.Domain.Carro;
import br.com.livroandroid.carrosfinal.Domain.CarroDB;
import br.com.livroandroid.carrosfinal.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CarroFragment extends Fragment {

    private Carro carro;
    private TextView textdesc;
    public CarroFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_carro, container, false);
         textdesc = (TextView) view.findViewById(R.id.tDesc);
        carro = (Carro) getArguments().getParcelable("carro");
        setHasOptionsMenu(true);
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_frag_carro,menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_edit) {
            //Toast.makeText(getContext(),"Editar: " + carro.nome,Toast.LENGTH_SHORT).show();
            Toast.makeText(getContext(), "Editar: " + carro.nome, Toast.LENGTH_SHORT).show();
            EditarCarroDialog.show(getFragmentManager(), carro, new EditarCarroDialog.Callback() {
                @Override
                public void onCarroUpdate(Carro carro) {
                    CarrosApplication.getInstance().getBus().post("refresh");
                    Toast.makeText(getContext(), "Carro ["+ carro.nome +"] atualizado", Toast.LENGTH_SHORT).show();
                    CarroDB db = new CarroDB(getContext());
                    db.save(carro);
                    CarroActivity a = new CarroActivity();
                    Bundle args = new Bundle();
                    Intent init = new Intent(getContext(),CarroActivity.class);
                    init.putExtra("carro",carro);
                    startActivity(init);
                }
            });
            return true;
        } else if (item.getItemId() == R.id.action_delete) {
            Toast.makeText(getContext(),"Deletar: " + carro.nome,Toast.LENGTH_SHORT).show();
            DeletarCarroDialog.show(getFragmentManager(), new DeletarCarroDialog.Callback() {
                @Override
                public void onClickYes() {
                    CarrosApplication.getInstance().getBus().post("refresh");
                    Toast.makeText(getContext(),"Carro ["+ carro.nome +"] deletado. ",Toast.LENGTH_SHORT).show();
                    CarroDB db = new CarroDB(getActivity());
                    db.delete(carro);
                    getActivity().finish();
                }
            });
            return true;
        } else if (item.getItemId() == R.id.action_share) {
            Toast.makeText(getContext(),"Compartilhar: ",Toast.LENGTH_SHORT).show();
            return true;
        } else if (item.getItemId() == R.id.action_maps) {
            Toast.makeText(getContext(),"Mapa: ",Toast.LENGTH_SHORT).show();
            return true;
        } else if (item.getItemId() == R.id.action_video) {
            Toast.makeText(getContext(),"Video: ",Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        textdesc.setText(carro.desc);
        final ImageView image = (ImageView) getView().findViewById(R.id.img);
        Picasso.with(getContext()).load(carro.urlFoto).fit().into(image);
    }
}
