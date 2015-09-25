package com.dirk41.remotecontrol;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by lingchong on 15-9-24.
 */
public class RemoteControlFragment extends Fragment {
    private TextView mSelectedChannelTextView;
    private TextView mWorkingChannelTextView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_remote_control, container, false);

        mSelectedChannelTextView = (TextView) view.findViewById(R.id.text_view_selected_channel);
        mWorkingChannelTextView = (TextView) view.findViewById(R.id.text_view_working_channel);
//        Button zeroButton = (Button) view.findViewById(R.id.button_zero);
//        Button oneButton = (Button) view.findViewById(R.id.button_one);
//        Button enterButton = (Button) view.findViewById(R.id.button_enter);

        View.OnClickListener numberButtonListner = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button button = (Button) v;
                String workingChannelString = mWorkingChannelTextView.getText().toString();
                String clickedNumberString = button.getText().toString();
                if (workingChannelString.equals("0")) {
                    mWorkingChannelTextView.setText(clickedNumberString);
                } else {
                    mWorkingChannelTextView.setText(workingChannelString + clickedNumberString);
                }
            }
        };

//        zeroButton.setOnClickListener(numberButtonListner);
//        oneButton.setOnClickListener(numberButtonListner);
//        enterButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String workingChannelString = mWorkingChannelTextView.getText().toString();
//                if (workingChannelString.length() > 0) {
//                    mSelectedChannelTextView.setText(workingChannelString);
//                } else {
//                    Toast.makeText(getActivity(), "频道为空，请先选择频道！", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });

        TableLayout tableLayout = (TableLayout) view.findViewById(R.id.table_layout_remote_control);
        int number = 1;
        for (int i = 2; i < tableLayout.getChildCount() - 1; ++i) {
            TableRow tableRow = (TableRow) tableLayout.getChildAt(i);
            for (int j = 0; j < tableRow.getChildCount(); ++j) {
                Button button = (Button) tableRow.getChildAt(j);
                button.setText(String.valueOf(number));
                button.setOnClickListener(numberButtonListner);
                ++number;
            }
        }

        TableRow lastTableRow = (TableRow) tableLayout.getChildAt(tableLayout.getChildCount() - 1);
        Button deleteButton = (Button) lastTableRow.getChildAt(0);
        deleteButton.setText("Delete");
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mWorkingChannelTextView.setText("");
            }
        });

        Button zeroButton = (Button) lastTableRow.getChildAt(1);
        zeroButton.setText("0");
        zeroButton.setOnClickListener(numberButtonListner);

        Button enterButton = (Button) lastTableRow.getChildAt(2);
        enterButton.setText("Enter");
        enterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String workingChannelString = mWorkingChannelTextView.getText().toString();
                if (workingChannelString.length() > 0) {
                    mSelectedChannelTextView.setText(workingChannelString);
                    mWorkingChannelTextView.setText("");
                } else {
                    Toast.makeText(getActivity(), "频道为空，请先选择频道！", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }
}
