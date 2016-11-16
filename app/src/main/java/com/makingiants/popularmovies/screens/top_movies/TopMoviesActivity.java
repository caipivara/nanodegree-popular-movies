package com.makingiants.popularmovies.screens.top_movies;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.makingiants.api.repositories.Movie;
import com.makingiants.api.repositories.MovieRepository;
import com.makingiants.popularmovies.BuildConfig;
import com.makingiants.popularmovies.R;
import java.util.List;

public class TopMoviesActivity extends AppCompatActivity implements TopMoviesView {

  @BindView(R.id.movies_recycler_view) RecyclerView mMoviesRecyclerView;

  private TopMoviesPresenter mPresenter;
  private MoviesAdapter mAdapter;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.top_movies_activity);
    ButterKnife.bind(this);

    mPresenter = new TopMoviesPresenter();

    mAdapter = new MoviesAdapter();
    mMoviesRecyclerView.setAdapter(mAdapter);
    mMoviesRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
  }

  @Override
  protected void onResume() {
    super.onResume();
    mPresenter.onAttach(this, new MovieRepository(BuildConfig.POPULAR_THE_MOVIE_API_KEY));
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    mPresenter.deAttach();
  }

  @Override
  public void showItems(List<Movie> items) {
    mAdapter.addItems(items);
  }
}