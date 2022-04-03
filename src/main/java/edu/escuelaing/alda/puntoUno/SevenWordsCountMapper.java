package edu.escuelaing.alda.puntoUno;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;
import org.apache.hadoop.mapred.Mapper;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class SevenWordsCountMapper extends MapReduceBase implements Mapper<LongWritable, Text, Text, IntWritable>{

    @Override
    public void map(LongWritable key, Text value, OutputCollector<Text, IntWritable> output, Reporter reporter) throws IOException {
        String[] words = new String[]{"TRUMP", "DICTATOR", "MAGA", "IMPEACH", "DRAIN", "SWAP", "CHANGE"};
        List<String> wordsToCheck = Arrays.asList(words);
        String line = value.toString();
        JSONParser parser = new JSONParser();
        try {
            JSONObject json = (JSONObject) parser.parse(line);
            String textField = (String) json.get("text");
            String[] wordsSeparated = textField.split(" ");
            for (String word: wordsSeparated) {
                String wordCapital = word.toUpperCase();
                for (String wordToFind: wordsToCheck) {
                    if(wordCapital.equals(wordToFind)){
                        output.collect(new Text(wordToFind), new IntWritable(1));
                    }
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
