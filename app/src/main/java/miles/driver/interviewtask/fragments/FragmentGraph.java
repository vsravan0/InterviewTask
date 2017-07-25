package miles.driver.interviewtask.fragments;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;

import miles.driver.interviewtask.R;

public class FragmentGraph extends Fragment {

    private PieChart mChart;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view =  inflater.inflate(R.layout.layout_graph, container, false);
        return  view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setIntialValues();
    }

    private void setIntialValues() {

        if(mChart==null)
            mChart=(PieChart)getView().findViewById(R.id.id_pie_chart);

        mChart.getDescription().setEnabled(false);

        mChart.setCenterText(generateCenterText());
        mChart.setCenterTextSize(10f);
        //  mChart.setCenterTextTypeface(tf);

        // radius of the center hole in percent of maximum radius
        mChart.setHoleRadius(60f);
        mChart.setTransparentCircleRadius(0f);
        mChart.setDrawEntryLabels(false);

        Legend l = mChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.CENTER);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setDrawInside(false);

        mChart.setData(generatePieData());

    }

    /**
     * generates less data (1 DataSet, 4 values)
     * @return
     */
    protected PieData generatePieData() {

        int count = 4;

        ArrayList<PieEntry> entries = new ArrayList<PieEntry>();

        entries.add(new PieEntry(30, "Ecuador      30%"));
        entries.add(new PieEntry(10, "Brasil       10%"));
        entries.add(new PieEntry(10, "Argentina    10%"));
        entries.add(new PieEntry(9, "Peru         09%"));
        entries.add(new PieEntry(8, "Colombia     08%"));
        entries.add(new PieEntry(8, "Bolivia      08%"));
        entries.add(new PieEntry(25, "Others       25%"));


        PieDataSet ds = new PieDataSet(entries, "");
        ds.setColors(VORDIPLOM_COLORS);

        ds.setSliceSpace(0f);
        ds.setValueTextSize(0f);

        PieData d = new PieData(ds);
        // d.setValueTypeface(tf);

        return d;
    }

    public static final int[] VORDIPLOM_COLORS = {
            Color.rgb(201, 34, 12), Color.rgb(138, 28, 123), Color.rgb(84, 1, 117),
            Color.rgb(13, 78, 163), Color.rgb(75, 164, 227),Color.rgb(155, 184, 29), Color.rgb(230, 190, 11)
    };

    private SpannableString generateCenterText() {
        SpannableString s = new SpannableString("578\n Votes");
        s.setSpan(new RelativeSizeSpan(2f), 0, 10, 0);
        s.setSpan(new ForegroundColorSpan(Color.GRAY), 10, s.length(), 0);
        return s;
    }


}
