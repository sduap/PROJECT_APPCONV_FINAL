package com.unipiloto.project_appconv_final.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.unipiloto.project_appconv_final.R;
import com.unipiloto.project_appconv_final.Services.ApproveReportActivity;
import com.unipiloto.project_appconv_final.Services.ReportsView;

import java.util.ArrayList;

public class ListReportsAdapter extends RecyclerView.Adapter<ListReportsAdapter.ReportViewHolder> {

    ArrayList<ReportsView> listReports;

    public ListReportsAdapter(ArrayList<ReportsView> listReports){
        this.listReports = listReports;
    }

    @NonNull
    @Override
    public ReportViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_report,null, false);
        return new ReportViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReportViewHolder holder, int position) {
        holder.textReportAddress.setText(listReports.get(position).getReportAddress());
        holder.textReportDate.setText(listReports.get(position).getReportDate());
        holder.textReportTime.setText(listReports.get(position).getReportTime());
        holder.textReportCategory.setText(listReports.get(position).getReportCategory());
        holder.textReportDescription.setText(listReports.get(position).getReportDescription());
        holder.textReportStatus.setText(listReports.get(position).getReportStatus());
    }

    @Override
    public int getItemCount() {
        return listReports.size();
    }

    public class ReportViewHolder extends RecyclerView.ViewHolder {

        TextView textReportAddress, textReportDate, textReportTime, textReportCategory, textReportDescription, textReportStatus;
        public ReportViewHolder(@NonNull View itemView) {
            super(itemView);

            textReportAddress = itemView.findViewById(R.id.textReportAddress);
            textReportDate = itemView.findViewById(R.id.textReportDate);
            textReportTime = itemView.findViewById(R.id.textReportTime);
            textReportCategory = itemView.findViewById(R.id.textReportCategory);
            textReportDescription = itemView.findViewById(R.id.textReportDescription);
            textReportStatus = itemView.findViewById(R.id.textReportStatus);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = v.getContext();
                    Intent intent = new Intent(context, ApproveReportActivity.class);
                    intent.putExtra("ID", listReports.get(getAdapterPosition()).getId());
                }
            });
        }
    }
}
