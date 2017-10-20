package com.example.hallbook.hallbooking.fcm;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hallbook.hallbooking.R;
import com.example.hallbook.hallbooking.entity.Owner;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by ARUN SUTHAR on 17-10-2017.
 */

public class OwnersAdapter extends RecyclerView.Adapter<OwnerViewHolder>{

        private ArrayList<Owner> mOwnerList;
        private Context mContext;
        private String mSearchString;

        public OwnersAdapter(ArrayList<Owner> OwnerList , Context context)
        {
            this.mOwnerList = OwnerList;
            this.mContext = context;
        }

        public void notifyDataChanged(String query) {
        mSearchString = query;
        notifyDataSetChanged();
    }


    @Override
        public OwnerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_hall,parent,false);
            return new OwnerViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(OwnerViewHolder holder, int position) {
            final Owner owner = mOwnerList.get(position);

            if(!TextUtils.isEmpty(mSearchString)) {

                holder.ownerstateView.setText(Utils.highlight(mSearchString, owner.getOwnerstate()));
                holder.ownerstateView.setText(Utils.highlight(mSearchString, owner.getOwnercity()));

            }else {

                holder.ownercityView.setText("Hall City" + owner.getOwnercity());
                holder.ownerstateView.setText("Hall State" + owner.getOwnerstate());
            }

            holder.hallnameView.setText("Hall Name:" + owner.getHallname());


            if (!TextUtils.isEmpty(owner.getImageurl())) {
                Picasso.with(mContext).load(owner.getImageurl())
                        .placeholder(R.drawable.ic_launcher)
                        .resize(80, 80)
                        .into(holder.imageurlView);
            }

            holder.item.setOnClickListener(new View.OnClickListener() {
                @Override

                public void onClick(final View view) {
                    if (PreferenceUtils.isLoggedIn(mContext))
                    {
                        Intent contactIntent = new Intent(mContext, ContactActivity.class);
                        contactIntent.putExtra("owner", owner);
                        mContext.startActivity(contactIntent);
                    }
                    else
                    {
                        Utils.showToast(mContext,"Please login to view details");
                    }
                }
            });
        }



        @Override
        public int getItemCount() {
            return mOwnerList.size();
        }
    }



