package edu.escuelaing.alda.puntoUno;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.*;

public class SevenWordsCount {
    public static void main(String[] args) throws Exception{
        JobConf conf = new JobConf(SevenWordsCount.class);
        conf.setJobName("wordcount");

        conf.setOutputKeyClass(Text.class);
        conf.setOutputValueClass(IntWritable.class);

        conf.setMapperClass(SevenWordsCountMapper.class);
        conf.setCombinerClass(SevenWordsCountReduce.class);
        conf.setReducerClass(SevenWordsCountReduce.class);

        conf.setInputFormat(TextInputFormat.class);
        conf.setOutputFormat(TextOutputFormat.class);

        FileInputFormat.setInputPaths(conf, new Path(args[0]));
        FileOutputFormat.setOutputPath(conf, new Path(args[1]));

        JobClient.runJob(conf);
    }
}
