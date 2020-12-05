package com.example.pas_11rpl2_irfansyah;

import android.util.Log;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class RealmHelper {
    Realm realm;
    public RealmHelper(Realm realm) {
        this.realm = realm;
    }

    public void save(final com.example.pas_11rpl2_irfansyah.ModelTeamsRealm movieModel) {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                if (realm != null) {
                    Log.e("Created", "Database was created");
                    Number currentIdNum = realm.where(com.example.pas_11rpl2_irfansyah.ModelTeamsRealm.class).max("id");
                    int nextId;
                    if (currentIdNum == null) {
                        nextId = 1;
                    } else {
                        nextId = currentIdNum.intValue() + 1;
                    }
                    movieModel.setId(nextId);
                    com.example.pas_11rpl2_irfansyah.ModelTeamsRealm model = realm.copyToRealm(movieModel);
                } else {
                    Log.e("ppppp", "execute: Database not Exist");
                }
            }
        });
    }
    // untuk memanggil semua data
    public List<com.example.pas_11rpl2_irfansyah.ModelTeamsRealm> getAllTeams(){
        RealmResults<com.example.pas_11rpl2_irfansyah.ModelTeamsRealm> results = realm.where(com.example.pas_11rpl2_irfansyah.ModelTeamsRealm.class).findAll();
        return results;
    }

    public void delete(Integer id){
        final RealmResults<com.example.pas_11rpl2_irfansyah.ModelTeamsRealm> model = realm.where(com.example.pas_11rpl2_irfansyah.ModelTeamsRealm.class).equalTo("id", id).findAll();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                model.deleteFromRealm(0);
            }
        });
    }
}
