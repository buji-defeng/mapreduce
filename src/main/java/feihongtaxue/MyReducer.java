package feihongtaxue;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class MyReducer extends Reducer<Text,FlowBean,Text,FlowBean> {


    /**
     * 统计总上行流量、总下行流量类
     */

    @Override
    protected void reduce(Text key, Iterable<FlowBean> values, Context context) throws IOException, InterruptedException {
        long sumUpFlow = 0;
        long sumDownFlow = 0;
        for(FlowBean value:values){
            sumUpFlow += value.getUpflow();
            sumDownFlow += value.getUpflow();
        }
        context.write(key,new FlowBean(sumUpFlow,sumDownFlow));
    }

}