package com.petmarkets2020.service;

import java.util.HashMap;

import org.springframework.stereotype.Service;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.petmarkets2020.model.Rankings;
import com.petmarkets2020.model.Utils;

@Service
public class RankingsServices {
	public static final String COL_NAME = "Rankings";
	public HashMap<String, Rankings> hmRankings;
	public HashMap<String, HashMap<String, Rankings>> rankings = new HashMap<String, HashMap<String, Rankings>>();


	public HashMap<String, HashMap<String, Rankings>> rankings() {

		Utils.connectFireBase(COL_NAME).addValueEventListener(new ValueEventListener() {

			@Override
			public void onDataChange(DataSnapshot snapshot) {
				for (DataSnapshot data : snapshot.getChildren()) {
					hmRankings = new HashMap<String, Rankings>();
					data.getChildren().forEach(dataSnap -> {
						hmRankings.put(dataSnap.getKey(), dataSnap.getValue(Rankings.class));
					});
					rankings.put(data.getKey(), hmRankings);
				}
			}

			@Override
			public void onCancelled(DatabaseError error) {

			}
		});
		return rankings;

	}

}
