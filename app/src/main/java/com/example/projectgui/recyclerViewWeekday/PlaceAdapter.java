package com.example.projectgui.recyclerViewWeekday;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectgui.R;

import java.util.ArrayList;

public class PlaceAdapter extends RecyclerView.Adapter<PlaceAdapter.PlaceViewHolder> {
    private final LayoutInflater inflater;
    private ArrayList<Place> places;

    public PlaceAdapter(LayoutInflater inflater, ArrayList<Place> places) {
        this.inflater = inflater;
        this.places = places;
    }

    @NonNull
    @Override
    public PlaceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_place, parent, false);
        return new PlaceViewHolder(view);
    }

    public void addPlace(Place newPlace) {
        ArrayList<Place> x = new ArrayList<>();
        x.addAll(places);
        x.add(newPlace);
        places = x;
        notifyDataSetChanged();
    }

    public void update(ArrayList<Place> newPlaces) {
        places = newPlaces;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull PlaceViewHolder holder, int position) {
        Place place = places.get(position);
        holder.underground.setText(place.getUnderground().getName());
        holder.place.setText(place.getAddress());
    }

    @Override
    public int getItemCount() {
        return places.size();
    }

    public static class PlaceViewHolder extends RecyclerView.ViewHolder {
        final CardView color;
        final TextView underground, place;
        public PlaceViewHolder(@NonNull View itemView) {
            super(itemView);
            color = itemView.findViewById(R.id.cv_undergroundColor);
            underground = itemView.findViewById(R.id.tv_underground);
            place = itemView.findViewById(R.id.tv_placeAddress);
        }
    }
}
