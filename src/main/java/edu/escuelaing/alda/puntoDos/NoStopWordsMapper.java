package edu.escuelaing.alda.puntoDos;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class NoStopWordsMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
    @Override
    protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, IntWritable>.Context context) throws IOException, InterruptedException {
        IntWritable one = new IntWritable(1);
        Text word = new Text();
        StringTokenizer tokens = new StringTokenizer(value.toString(), ",");
        while (tokens.hasMoreTokens()) {
            String token = tokens.nextToken();
            
        }
    }
    
}
