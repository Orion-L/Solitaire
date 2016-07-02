package orion.solitaire;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.LinkedList;

public class ImageAdapter extends BaseAdapter {
    private Context c;
    LinkedList<Integer> images;

    public ImageAdapter(Context c) {
        this.c = c;
        images = new LinkedList<Integer>();
        images.add(c.getResources().getIdentifier("empty", "drawable", c.getPackageName()));
        images.add(c.getResources().getIdentifier("c11", "drawable", c.getPackageName()));
        images.add(c.getResources().getIdentifier("c5", "drawable", c.getPackageName()));
        images.add(c.getResources().getIdentifier("c7", "drawable", c.getPackageName()));
        images.add(c.getResources().getIdentifier("c9", "drawable", c.getPackageName()));
        images.add(c.getResources().getIdentifier("c1", "drawable", c.getPackageName()));
    }

    @Override
    public int getCount() {
        return images.size();
    }

    @Override
    public Object getItem(int position) {
        return images.get(position);
    }

    @Override
    public long getItemId(int position) {
        return images.get(position);
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        if (view == null) {
            LayoutInflater vi = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = vi.inflate(R.layout.item, null);
        }

        ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
        imageView.setImageResource(images.get(position));

        return view;
    }
}
