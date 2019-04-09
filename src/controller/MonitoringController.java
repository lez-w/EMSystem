package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MonitoringController implements Initializable {
    @FXML
    private LineChart<String, Number> lineChart;
    XYChart.Series<String,Number> series,series1,series2;//温度值，通过Number与LineChart连接起来

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        series = new XYChart.Series<>();
        series.setName("温度1");
        series1 = new XYChart.Series<>();
        series1.setName("温度2");
        series2 = new XYChart.Series<>();
        series2.setName("温度3");

        lineChart.getData().add(series);
        lineChart.getData().add(series1);
        lineChart.getData().add(series2);


        Thread thread = new Thread(new TemperatureReader());
        thread.start();
    }

    class TemperatureReader implements Runnable{
        @Override
        public void run() {
            try {
                int ctr = 1;
               while (true){
                   String line = Double.toString((30+Math.random()*(40-30+1)));//line=reader.readLine()
                   String line1 = Double.toString(59);//告警值
                   String line2 = Double.toString((50+Math.random()*(60-50+1)));

                   Double temp = Double.parseDouble(line);//获取温度
                   Double temp1 = Double.parseDouble(line1);
                   Double temp2 = Double.parseDouble(line2);
                   series.getData().add(new XYChart.Data<>(ctr+"s",temp));
                   series1.getData().add(new XYChart.Data<>(ctr+"s",temp1));
                   series2.getData().add(new XYChart.Data<>(ctr+"s",temp2));
                    ctr++;
                   System.out.println("Match Found = "+line);
                   System.out.println("Match Found = "+line1);
                   System.out.println("Match Found = "+line2);
                   System.out.println("----------------------");
                    Thread.sleep(1000);//2s执行一次数据读取
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
