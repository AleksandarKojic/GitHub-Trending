package com.xapo.githubtrending.model;

/**
 * Created by Alexandar on 3/2/2018.
 */

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GitHubResponseObject implements Parcelable {

    @SerializedName("total_count")
    @Expose
    private Integer totalCount;
    @SerializedName("incomplete_results")
    @Expose
    private Boolean incompleteResults;
    @SerializedName("items")
    @Expose
    private List<GitHubRepository> items = null;
    public final static Parcelable.Creator<GitHubResponseObject> CREATOR = new Creator<GitHubResponseObject>() {


        @SuppressWarnings({
                "unchecked"
        })
        public GitHubResponseObject createFromParcel(Parcel in) {
            return new GitHubResponseObject(in);
        }

        public GitHubResponseObject[] newArray(int size) {
            return (new GitHubResponseObject[size]);
        }

    };

    protected GitHubResponseObject(Parcel in) {
        this.totalCount = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.incompleteResults = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        in.readList(this.items, (GitHubRepository.class.getClassLoader()));
    }

    public GitHubResponseObject() {
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Boolean getIncompleteResults() {
        return incompleteResults;
    }

    public void setIncompleteResults(Boolean incompleteResults) {
        this.incompleteResults = incompleteResults;
    }

    public List<GitHubRepository> getItems() {
        return items;
    }

    public void setItems(List<GitHubRepository> items) {
        this.items = items;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(totalCount);
        dest.writeValue(incompleteResults);
        dest.writeList(items);
    }

    public int describeContents() {
        return 0;
    }

}
