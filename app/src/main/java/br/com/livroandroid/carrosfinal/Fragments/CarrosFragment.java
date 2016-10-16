package br.com.livroandroid.carrosfinal.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import br.com.livroandroid.carrosfinal.Activities.CarroActivity;
import br.com.livroandroid.carrosfinal.Adapter.CarroAdapter;
import br.com.livroandroid.carrosfinal.Domain.Carro;
import br.com.livroandroid.carrosfinal.Domain.CarroService;
import br.com.livroandroid.carrosfinal.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CarrosFragment extends Fragment {

    private int tipo;
    protected RecyclerView recyclerView;
    private List<Carro> carros;
    public CarrosFragment() {
        // Required empty public constructor
    }

    public static CarrosFragment newInstance(int tipo) {
        Bundle args = new Bundle();
        args.putInt("tipo",tipo);
        CarrosFragment f = new CarrosFragment();
        f.setArguments(args);
        return f;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            this.tipo = getArguments().getInt("tipo");

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_carros, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        taskcarros();
    }

    private void taskcarros() {
        this.carros = CarroService.getCarros(getContext(),tipo);
        recyclerView.setAdapter(new CarroAdapter(carros, getContext(),onClickCarro()));
    }

    private CarroAdapter.CarroOnClickListener onClickCarro() {
        return new CarroAdapter.CarroOnClickListener() {
            @Override
            public void onClickCarro(View view, int idx) {
                Carro c = carros.get(idx);
                Toast.makeText(getContext(),"Carro: " + c.nome, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getContext(), CarroActivity.class);
                intent.putExtra("carro",c);
                startActivity(intent);
            }
        };
    }
}
