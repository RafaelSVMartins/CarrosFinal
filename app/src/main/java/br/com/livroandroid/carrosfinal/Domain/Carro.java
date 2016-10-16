package br.com.livroandroid.carrosfinal.Domain;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by Rrafael on 14/10/2016.
 */

public class Carro implements Parcelable {

    private static final long serialVersionUID = 6601006766832473969L;
    public long id;
    public String tipo;
    public String nome;
    public String desc;
    public String urlFoto;
    public String urlInfo;
    public String urlVideo;
    public String lagitude;
    public String longitude;

    @Override
    public String toString() {
        return "Carro{" +
                "nome='" + nome + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.id);
        dest.writeString(this.tipo);
        dest.writeString(this.nome);
        dest.writeString(this.desc);
        dest.writeString(this.urlFoto);
        dest.writeString(this.urlInfo);
        dest.writeString(this.urlVideo);
        dest.writeString(this.lagitude);
        dest.writeString(this.longitude);
    }

    public Carro() {
    }

    protected Carro(Parcel in) {
        this.id = in.readLong();
        this.tipo = in.readString();
        this.nome = in.readString();
        this.desc = in.readString();
        this.urlFoto = in.readString();
        this.urlInfo = in.readString();
        this.urlVideo = in.readString();
        this.lagitude = in.readString();
        this.longitude = in.readString();
    }

    public static final Parcelable.Creator<Carro> CREATOR = new Parcelable.Creator<Carro>() {
        @Override
        public Carro createFromParcel(Parcel source) {
            return new Carro(source);
        }

        @Override
        public Carro[] newArray(int size) {
            return new Carro[size];
        }
    };
}
