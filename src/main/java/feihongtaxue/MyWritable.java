package feihongtaxue;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;


public class MyWritable {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        args = new String[]{"C:\\Users\\15877\\Desktop\\极客大数据\\课件\\HTTP_20130313143750.dat","C:\\Users\\15877\\Desktop\\极客大数据\\课件\\1.txt"};

        //1.获取job信息
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf);

        //2.加载jar包
        job.setJarByClass(MyWritable.class);

        //3.关联map和reduce
        job.setMapperClass(MyMapper.class);
        job.setReducerClass(MyReducer.class);

        //4.设置最终输出类型
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(FlowBean.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(FlowBean.class);

        //5.设置输入和输出路径
        FileInputFormat.setInputPaths(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        //6.提交job任务
        job.waitForCompletion(true);
    }
}