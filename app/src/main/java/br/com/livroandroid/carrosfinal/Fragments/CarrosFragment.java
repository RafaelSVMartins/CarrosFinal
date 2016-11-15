package br.com.livroandroid.carrosfinal.Fragments;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.squareup.otto.Subscribe;

import java.io.IOException;
import java.util.List;

import br.com.livroandroid.carrosfinal.CarrosApplication;
import livroandroid.lib.fragment.BaseFragment.TaskListener;
import br.com.livroandroid.carrosfinal.Activities.CarroActivity;
import br.com.livroandroid.carrosfinal.Adapter.CarroAdapter;
import br.com.livroandroid.carrosfinal.Domain.Carro;
import br.com.livroandroid.carrosfinal.Domain.CarroService;
import br.com.livroandroid.carrosfinal.R;
import livroandroid.lib.fragment.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class CarrosFragment extends BaseFragment {
    private SwipeRefreshLayout SwipeLayout;
    private ProgressBar progressBar;
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
        CarrosApplication.getInstance().getBus().register(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_carros, container, false);
        progressBar = (ProgressBar) view.findViewById(R.id.progress1);
        SwipeLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipetorefresh);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);
        SwipeLayout.setOnRefreshListener(OnRefreshListener());
        SwipeLayout.setColorSchemeResources(
                R.color.refresh_progress_1,
                R.color.refresh_progress_2,
                R.color.refresh_progress_3);
        return view;
    }

    private SwipeRefreshLayout.OnRefreshListener OnRefreshListener() {
        return new SwipeRefreshLayout.OnRefreshListener()   {

            @Override
            public void onRefresh() {
                taskcarros(true);
            }
        };

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        taskcarros(false);
    }

    @Subscribe
    public void taskcarros(boolean pullToRefresh) {
        new GetCarrosTask(pullToRefresh).execute();
        //startTask("carros", new getCarrosTask2(),R.id.progress1);
    }


    private class getCarrosTask2 implements TaskListener<List<Carro>> {
        private boolean pull;
        public getCarrosTask2(boolean pullToRefresh) {
            super();
            this.pull = pullToRefresh;
        }
        @Override
        public List<Carro> execute() throws Exception {
            //Busca no background
            Thread.sleep(500);
            return CarroService.getCarros(getContext(),tipo,pull);
        }

        @Override
        public void updateView(List<Carro> response) {
            if (carros != null) {
                //Salva a lista carros no atributo da classe
                CarrosFragment.this.carros = carros;
                //Atualiza a view na UI thread
                recyclerView.setAdapter(new CarroAdapter(carros, getContext(),onClickCarro()));
            }
        }

        @Override
        public void onError(Exception exception) {
            Toast.makeText(getContext(),"Erro na busca dos carros!",Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCancelled(String cod) {
        }
    }

    private class GetCarrosTask extends AsyncTask<Void,Void,List<Carro>> {
        private boolean pull;
        public GetCarrosTask(boolean pullToRefresh) {
            super();
            this.pull = pullToRefresh;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            if(pull == false) {
                progressBar.setVisibility(View.VISIBLE);
            }

        }

        @Override
        protected List<Carro> doInBackground(Void... params) {
            try {
                return CarroService.getCarros(getContext(),tipo,pull);
            } catch (IOException e) {
                Log.e(e.getMessage(), String.valueOf(e));
                return null;
            }
        }

        @Override
        protected void onPostExecute(List<Carro> carros) {
            if (carros != null) {
               CarrosFragment.this.carros = carros;
                recyclerView.setAdapter(new CarroAdapter(carros,getContext(),onClickCarro()));
                if (pull == false) {
                    progressBar.setVisibility(View.GONE);
                }
            }
        }
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

    @Override
    public void onDestroy() {
        super.onDestroy();
        CarrosApplication.getInstance().getBus().unregister(this);
    }
}
