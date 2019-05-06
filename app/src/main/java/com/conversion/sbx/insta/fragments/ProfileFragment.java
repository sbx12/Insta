package com.conversion.sbx.insta.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.conversion.sbx.insta.Post;
import com.conversion.sbx.insta.PostsAdapter;
import com.conversion.sbx.insta.R;
import com.conversion.sbx.insta.User;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

public class ProfileFragment extends PostsFragment {

    private RecyclerView rvPosts;
    private TextView tvProfileUsername;
    private TextView tvPostNumber;
    private TextView tvProfileDescription;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        tvProfileUsername = view.findViewById(R.id.tvProfileUsername);

        rvPosts = view.findViewById(R.id.rvPosts);
        tvPostNumber = view.findViewById(R.id.tvPostNumber);
        tvProfileDescription = view.findViewById(R.id.tvProfileDescription);

        //RecyclerView
        mPosts = new ArrayList<>();
        adapter = new PostsAdapter(getContext(), mPosts, "PostsFragment");
        rvPosts.setAdapter(adapter);
        rvPosts.setLayoutManager(new LinearLayoutManager(getContext()));


        queryUser();
        queryPosts();
    }

    protected void queryUser() {
        ParseUser user = ParseUser.getCurrentUser();
        tvProfileUsername.setText(user.getUsername());

    }

    protected void queryPosts() {
        ParseQuery<Post> postQuery = new ParseQuery<Post>(Post.class);
        postQuery.include(Post.KEY_USER);
        postQuery.setLimit(20);
        postQuery.whereEqualTo(Post.KEY_USER, ParseUser.getCurrentUser());
        postQuery.addDescendingOrder(Post.KEY_CREATED_AT);
        postQuery.findInBackground(new FindCallback<Post>() {
            @Override
            public void done(List<Post> posts, ParseException e) {
                if(e != null) {
                    Log.e("Query", "ERRROR");
                    e.printStackTrace();
                    return;
                }

                mPosts.addAll(posts);
                adapter.notifyDataSetChanged();


                for(int i = 0; i < posts.size(); i++){
                    Post post = posts.get(i);
                    Log.d("Post", post.getDescription() + " " + post.getUser().getUsername());
                }
            }
        });
    }
}
