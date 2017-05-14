package com.toan_itc.data.model.news;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Toan.IT
 * Date: 09/07/2016
 */
public class News extends RealmObject{
    @PrimaryKey
    private int Id;
    private String Title;
    private RealmList<Tinhot> Tinhot = new RealmList<>();
    private RealmList<Vnexpress> Vnexpress = new RealmList<>();
    private RealmList<Dantri> Dantri = new RealmList<>();
    private RealmList<Ngoisao> Ngoisao = new RealmList<>();
    private RealmList<Kenh14> Kenh14 = new RealmList<>();
    private RealmList<Dspl> Dspl = new RealmList<>();
    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    /**
     *
     * @return
     * The Title
     */
    public String getTitle() {
        return Title;
    }

    /**
     *
     * @param Title
     * The Title
     */
    public void setTitle(String Title) {
        this.Title = Title;
    }

    public RealmList<com.toan_itc.data.model.news.Tinhot> getTinhot() {
        return Tinhot;
    }

    public void setTinhot(RealmList<com.toan_itc.data.model.news.Tinhot> tinhot) {
        Tinhot = tinhot;
    }

    public RealmList<com.toan_itc.data.model.news.Vnexpress> getVnexpress() {
        return Vnexpress;
    }

    public void setVnexpress(RealmList<com.toan_itc.data.model.news.Vnexpress> vnexpress) {
        Vnexpress = vnexpress;
    }

    public RealmList<com.toan_itc.data.model.news.Dantri> getDantri() {
        return Dantri;
    }

    public void setDantri(RealmList<com.toan_itc.data.model.news.Dantri> dantri) {
        Dantri = dantri;
    }

    public RealmList<com.toan_itc.data.model.news.Ngoisao> getNgoisao() {
        return Ngoisao;
    }

    public void setNgoisao(RealmList<com.toan_itc.data.model.news.Ngoisao> ngoisao) {
        Ngoisao = ngoisao;
    }

    public RealmList<com.toan_itc.data.model.news.Kenh14> getKenh14() {
        return Kenh14;
    }

    public void setKenh14(RealmList<com.toan_itc.data.model.news.Kenh14> kenh14) {
        Kenh14 = kenh14;
    }

    public RealmList<com.toan_itc.data.model.news.Dspl> getDspl() {
        return Dspl;
    }

    public void setDspl(RealmList<com.toan_itc.data.model.news.Dspl> dspl) {
        Dspl = dspl;
    }
}
