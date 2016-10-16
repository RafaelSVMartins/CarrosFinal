package br.com.livroandroid.carrosfinal.Adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

import br.com.livroandroid.carrosfinal.Domain.Carro;
import br.com.livroandroid.carrosfinal.R;

/**
 * Created by Rrafael on 14/10/2016.
 */

public class CarroAdapter extends RecyclerView.Adapter<CarroAdapter.CarrosViewHolder> {
    protected static final String TAG ="livroandroid";
    private final List<Carro> carros;
    private final Context context;
    private CarroOnClickListener carrOnClickListener;

    public CarroAdapter(List<Carro> carros, Context context, CarroOnClickListener carrOnClickListener) {
        this.carros = carros;
        this.context = context;
        this.carrOnClickListener = carrOnClickListener;
    }

    public interface CarroOnClickListener {
        public void onClickCarro(View view, int idx);
    }
    @Override
    public CarroAdapter.CarrosViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_carro,viewGroup,false);
        CarrosViewHolder holder = new CarrosViewHolder(view);
        return holder;
    }


    @Override
    public void onBindViewHolder(final CarroAdapter.CarrosViewHolder holder, final int position) {
        Carro c = carros.get(position);
        holder.tNome.setText(c.nome);
        holder.progressBar.setVisibility(View.VISIBLE);
        Picasso.with(context).load(c.urlFoto).fit()
                .into(holder.img, new Callback() {
                    @Override
                    public void onSuccess() {
                        holder.progressBar.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError() {
                        holder.progressBar.setVisibility(View.GONE);
                    }
                });
        if (carrOnClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    carrOnClickListener.onClickCarro(holder.itemView,position);
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return this.carros != null ? this.carros.size() : 0;
    }

    public static class CarrosViewHolder extends RecyclerView.ViewHolder {
        public TextView tNome;
        ImageView img;
        ProgressBar progressBar;
        CardView cardView;

        public CarrosViewHolder(View view) {
            super(view);

            tNome = (TextView) view.findViewById(R.id.text);
            img = (ImageView) view.findViewById(R.id.img);
            progressBar = (ProgressBar) view.findViewById(R.id.progressImg);
            cardView =(CardView) view.findViewById(R.id.card_view);
        }
    }
}
