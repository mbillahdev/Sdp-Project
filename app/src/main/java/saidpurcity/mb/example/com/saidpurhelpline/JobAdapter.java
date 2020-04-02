package saidpurcity.mb.example.com.saidpurhelpline;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;


public class JobAdapter extends RecyclerView.Adapter<JobAdapter.JobViewHolder> {

    Context mContext;
    private List<JobList> jobLists;

    public JobAdapter(Context mContext, List<JobList> jobLists) {
        this.mContext = mContext;
        this.jobLists = jobLists;
    }

    @NonNull
    @Override
    public JobViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        v= LayoutInflater.from(mContext).inflate(R.layout.job_item,parent,false);
        JobViewHolder viewHolder =new JobViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull JobViewHolder holder, int position) {

        holder.setData(jobLists.get(position).getUrl(),jobLists.get(position).getJobTitle(),jobLists.get(position).getJobTime(),jobLists.get(position).getLastDate());

        //holder.setData(jobLists.get(position).getUrl(),jobLists.get(position).getJobTitle(), jobLists.get(position).getSets());

    }

    @Override
    public int getItemCount() {
        return jobLists.size();
    }

    public static class JobViewHolder extends RecyclerView.ViewHolder{

        private TextView jobTitle;
        private TextView jobTime;
        private TextView lastDate;
        private ImageView jobThumbnail;

        public JobViewHolder(View itemView) {
            super(itemView);

            jobTitle = itemView.findViewById(R.id.jobTitle);
            jobTime = itemView.findViewById(R.id.jobTime);
            lastDate = itemView.findViewById(R.id.lastDate);
            jobThumbnail = itemView.findViewById(R.id.jobThumbnail);
        }


        private void setData(String url, final String jobTitle, String jobTime, String lastDate){

            Glide.with(itemView.getContext()).load(url).into(jobThumbnail);
            this.jobTitle.setText(jobTitle);
            this.jobTime.setText(jobTime);
            this.lastDate.setText(lastDate);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Toast.
                    Toast.makeText(itemView.getContext(),"Data Click", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
