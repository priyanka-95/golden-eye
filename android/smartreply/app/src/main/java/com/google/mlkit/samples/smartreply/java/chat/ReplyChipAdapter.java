/*
 * Copyright 2020 Google LLC. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.mlkit.samples.smartreply.java.chat;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.mlkit.nl.smartreply.SmartReplySuggestion;
import com.google.mlkit.samples.smartreply.R;

import java.util.ArrayList;
import java.util.List;

public class ReplyChipAdapter extends RecyclerView.Adapter<ReplyChipAdapter.ViewHolder> {

    public interface ClickListener {

        void onChipClick(@NonNull String chipText);

    }

    private List<SmartReplySuggestion> mSuggestions = new ArrayList<>();
    private ClickListener mListener;

    public ReplyChipAdapter(@NonNull ClickListener listener) {
        mListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.smart_reply_chip, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SmartReplySuggestion suggestion = mSuggestions.get(position);
        holder.bind(suggestion);
    }

    @Override
    public int getItemCount() {
        return mSuggestions.size();
    }

    public void setSuggestions(List<SmartReplySuggestion> suggestions) {
        mSuggestions.clear();
        mSuggestions.addAll(suggestions);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView text;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.text = itemView.findViewById(R.id.smartReplyText);
        }

        public void bind(final SmartReplySuggestion suggestion) {
            text.setText(suggestion.getText());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mListener.onChipClick(suggestion.getText());
                }
            });
        }
    }

}
