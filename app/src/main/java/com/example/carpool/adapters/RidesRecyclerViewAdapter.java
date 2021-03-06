package com.example.carpool.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.carpool.Constants;
import com.example.carpool.R;
import com.example.carpool.dialogFragments.ConfirmRideDialogFragment;
import com.example.carpool.modelClasses.RideAdsContent;

import java.util.ArrayList;

public class RidesRecyclerViewAdapter extends RecyclerView.Adapter<RidesRecyclerViewAdapter.ViewHolder> {

    private static final String TAG = RidesRecyclerViewAdapter.class.getName();
    private Context context;
    private ArrayList<RideAdsContent> rideAdsList;

    public RidesRecyclerViewAdapter(ArrayList<RideAdsContent> rideAdsList, Context context) {
        this.rideAdsList = rideAdsList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rides_card, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        RideAdsContent ride = rideAdsList.get(position);
        if (ride != null) {
            holder.destinationCity.setText(ride.getDestinationCity());
            holder.departureCity.setText(ride.getDepartureCity());
            holder.dateTime.setText(ride.getDepartureDate() + ", " + ride.getDepartureTime());
            holder.totalSeats.setText(String.valueOf(ride.getSeatsAvailable()));

            holder.parentCardView.setOnClickListener(view -> {
                ConfirmRideDialogFragment confirmRideDialogFragment = new ConfirmRideDialogFragment();
                Bundle bundle = new Bundle();
                bundle.putParcelable(Constants.POSTED_RIDE_CONTENTS, ride);
                confirmRideDialogFragment.setArguments(bundle);
                confirmRideDialogFragment.show(((AppCompatActivity) context).getSupportFragmentManager(), TAG);
            });
        }
    }

    @Override
    public int getItemCount() {
        return (rideAdsList == null) ? 0 : rideAdsList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        CardView parentCardView;
        TextView departureCity, destinationCity, totalSeats, dateTime;

        ViewHolder(@NonNull View itemView) {
            super(itemView);

            parentCardView = itemView.findViewById(R.id.cv_posted_ride);
            departureCity = itemView.findViewById(R.id.tv_current_location);
            destinationCity = itemView.findViewById(R.id.tv_dest_location);
            totalSeats = itemView.findViewById(R.id.tv_no_of_seats);
            dateTime = itemView.findViewById(R.id.tv_date_time);
        }
    }

}
