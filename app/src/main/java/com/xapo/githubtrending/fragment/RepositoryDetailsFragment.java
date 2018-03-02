package com.xapo.githubtrending.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.xapo.githubtrending.R;
import com.xapo.githubtrending.model.GitHubRepository;

/**
 * Fragment containing details about Github repository selected by user
 */
public class RepositoryDetailsFragment extends Fragment {

    public static String REPOSITORY = "repositoryList";

    private GitHubRepository mSelectedGitHubRepository;



    public static RepositoryDetailsFragment newInstance(GitHubRepository gitHubRepository) {
        Bundle args = new Bundle();
        args.putParcelable(REPOSITORY, gitHubRepository);
        RepositoryDetailsFragment fragment = new RepositoryDetailsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        mSelectedGitHubRepository = getArguments().getParcelable(REPOSITORY);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_repository_details, container, false);

        ImageView ownerAvatar = view.findViewById(R.id.owner_avatar);
        TextView starNumber = view.findViewById(R.id.star_number);
        TextView fullName = view.findViewById(R.id.full_name);
        TextView description = view.findViewById(R.id.description);

        starNumber.setText(Integer.toString(mSelectedGitHubRepository.getStargazersCount()));
        fullName.setText(mSelectedGitHubRepository.getDescription());
        description.setText(mSelectedGitHubRepository.getDescription());

        String avatarUrl = mSelectedGitHubRepository.getOwner().getAvatarUrl();
        Picasso.with(getActivity()).load(avatarUrl).error(R.drawable.avatar_placeholder).resize(860, 640).centerCrop().placeholder(R.drawable.avatar_placeholder).into(ownerAvatar);

        return view;
    }
}
