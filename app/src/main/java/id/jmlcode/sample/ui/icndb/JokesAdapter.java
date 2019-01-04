package id.jmlcode.sample.ui.icndb;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.jmlcode.sample.R;
import id.jmlcode.sample.model.bean.Joke;


public class JokesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final boolean isOntop = false;

    private final int VIEW_EMPTY = 2;
    private final int VIEW_ITEM = 1;

    private Context mContext;
    private LayoutInflater inflater;
    private List<Joke> mList;
    private OnItemClickListener onItemClickListener;

    public JokesAdapter(Context mContext, List<Joke> mList) {
        this.mContext = mContext;
        this.mList = mList;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getItemCount() {
        return (mList != null && mList.size() > 0) ? mList.size() : 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (mList.size() == 0) {
            return VIEW_EMPTY;
        } else {
            return VIEW_ITEM;
        }
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder vh;
        if (viewType == VIEW_ITEM) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_joke, parent, false);
            vh = new JokeViewHolder(v);
        } else {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_empty, parent, false);
            vh = new EmptyHolder(v);
        }
        return vh;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof JokeViewHolder) {
            JokeViewHolder JokeViewHolder = ((JokeViewHolder) holder);

            if (position == 0){
                JokeViewHolder.ivArrow.setVisibility(View.GONE);
                JokeViewHolder.tvOnTop.setVisibility(View.VISIBLE);
            }else {
                JokeViewHolder.ivArrow.setVisibility(View.VISIBLE);
                JokeViewHolder.tvOnTop.setVisibility(View.GONE);
            }

            JokeViewHolder.tvText.setText(mList.get(position).getJoke());


            JokeViewHolder.ivArrow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.onClick(mList.get(position), false);
                }
            });

            JokeViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.onClick(mList.get(position), true);
                }
            });
        } else {
            ((EmptyHolder) holder).tvEmpty.setText("Tidak ada data untuk ditampilkan");
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onClick(Joke data, boolean isOntop);
    }

    public static class JokeViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_text)
        TextView tvText;

        @BindView(R.id.tv_text_on_top)
        TextView tvOnTop;
        @BindView(R.id.iv_arrow)
        ImageView ivArrow;

        public JokeViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public static class EmptyHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_empty)
        TextView tvEmpty;

        public EmptyHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
