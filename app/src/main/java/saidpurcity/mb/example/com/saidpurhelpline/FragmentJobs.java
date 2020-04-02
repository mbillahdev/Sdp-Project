package saidpurcity.mb.example.com.saidpurhelpline;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FragmentJobs extends android.support.v4.app.Fragment {

    private RecyclerView jobListRecycler;
    private List<JobList> mJoblist;

    JobAdapter jobAdapter = new JobAdapter(getContext(),mJoblist);

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();

    View v;
    public FragmentJobs() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.jobs_fragment,container,false);
        jobListRecycler = v.findViewById(R.id.jobListRecycler);
        jobListRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        jobListRecycler.setAdapter(jobAdapter);
        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mJoblist = new ArrayList<>();

        myRef = FirebaseDatabase.getInstance().getReference().child("JobList");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
                    mJoblist.add(dataSnapshot1.getValue(JobList.class));
                    Toast.makeText(getContext(), "Job List Loaded", Toast.LENGTH_SHORT).show();
                }
                jobAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Toast.makeText(getContext(), "Job List Not Loaded", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
