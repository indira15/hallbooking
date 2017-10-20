package com.example.hallbook.hallbooking.fcm;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hallbook.hallbooking.R;

/**
 * Created by ARUN SUTHAR on 17-10-2017.
 */

public class OwnerViewHolder extends RecyclerView.ViewHolder {
        public TextView hallnameView;
        public TextView ownercityView;
        public TextView ownerstateView;
        public ImageView imageurlView;
        public View item;

        public OwnerViewHolder(View itemView) {
            super(itemView);
            hallnameView = (TextView) itemView.findViewById(R.id.hall_name);
            ownercityView = (TextView) itemView.findViewById(R.id.hall_ownerspinnercity);
            ownerstateView = (TextView) itemView.findViewById(R.id.hall_ownerspinnerstate);
            imageurlView = (ImageView) itemView.findViewById(R.id.hall_image);
            item = itemView;
        }
    }



