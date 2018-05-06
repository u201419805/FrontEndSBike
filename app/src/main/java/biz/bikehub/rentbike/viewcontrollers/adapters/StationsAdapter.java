package biz.bikehub.rentbike.viewcontrollers.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.androidnetworking.widget.ANImageView;

import java.util.List;

import biz.bikehub.rentbike.R;
import biz.bikehub.rentbike.models.Station;
import biz.bikehub.rentbike.viewcontrollers.activities.OrderActivity;

public class StationsAdapter
        extends RecyclerView.Adapter<StationsAdapter.ViewHolder>{

    private List<Station> stations;

    public StationsAdapter(List<Station> Stations) {
        this.stations = Stations;
    }

    public StationsAdapter() {
    }

    @NonNull
    @Override
    public StationsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_station, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull StationsAdapter.ViewHolder holder, int position) {
        holder.updateViews(stations.get(position));
    }

    @Override
    public int getItemCount() {
        return stations.size();
    }

    public List<Station> getStations() {
        return stations;
    }

    public StationsAdapter setStations(List<Station> Stations) {
        this.stations = Stations;
        return this;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ANImageView pictureImageView;
        private TextView nameTextView;
        private TextView addressTextView;
        private TextView capacityTextView;

        private ConstraintLayout StationLayout;

        public ViewHolder(View view) {
            super(view);
            pictureImageView = (ANImageView) view.findViewById(R.id.image_picture);
            nameTextView = (TextView) view.findViewById(R.id.text_name);
            addressTextView = (TextView) view.findViewById(R.id.text_address);
            capacityTextView = (TextView) view.findViewById(R.id.text_capacity);

            StationLayout = (ConstraintLayout) view.findViewById(R.id.layout_station);
        }

        public void updateViews(final Station station) {
            nameTextView.setText(station.getName());
            addressTextView.setText(station.getAddress());
            capacityTextView.setText(station.getCapacity());
            pictureImageView.setDefaultImageResId(R.mipmap.ic_launcher);
            pictureImageView.setErrorImageResId(R.mipmap.ic_launcher);
            pictureImageView.setImageUrl(station.getUrlimage());
            StationLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = v.getContext();
                    context.startActivity(new Intent(context, OrderActivity.class).putExtras(station.toBundle()));
                }
            });
        }
    }
    
    
}
