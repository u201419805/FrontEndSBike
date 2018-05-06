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
import biz.bikehub.rentbike.models.Order;
import biz.bikehub.rentbike.models.Station;
import biz.bikehub.rentbike.viewcontrollers.activities.OrderActivity;

public class OrdersAdapter extends RecyclerView.Adapter<OrdersAdapter.ViewHolder>{

    private List<Order> orders;

    public OrdersAdapter(List<Order> Orders) {
        this.orders = Orders;
    }

    public OrdersAdapter() {
    }

    @NonNull
    @Override
    public OrdersAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new OrdersAdapter.ViewHolder(
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_order, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull OrdersAdapter.ViewHolder holder, int position) {
        holder.updateViews(orders.get(position));
    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

    public List<Order> getOrders() {
        return orders;
    }

    public OrdersAdapter setOrders(List<Order> orders) {
        this.orders = orders;
        return this;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView stationTextView;
        private TextView orderDateTextView;
        private TextView statusTextView;

        private ConstraintLayout ordersLayout;

        public ViewHolder(View view) {
            super(view);
            stationTextView = (TextView) view.findViewById(R.id.text_station);
            orderDateTextView = (TextView) view.findViewById(R.id.text_order);
            statusTextView = (TextView) view.findViewById(R.id.text_status);

            ordersLayout = (ConstraintLayout) view.findViewById(R.id.layout_order);
        }

        public void updateViews(final Order order) {
            stationTextView.setText(order.getStationid());
            orderDateTextView.setText(order.getOrderdate());
            statusTextView.setText(order.getStatusText());

            ordersLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = v.getContext();
                    //context.startActivity(new Intent(context, OrderActivity.class).putExtras(station.toBundle()));
                }
            });
        }
    }


}