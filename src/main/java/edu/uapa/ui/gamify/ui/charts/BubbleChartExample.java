package edu.uapa.ui.gamify.ui.charts;

import com.github.appreciated.apexcharts .ApexCharts;
import com.github.appreciated.apexcharts.config.builder.*;
import com.github.appreciated.apexcharts.config.chart.Type;
import com.github.appreciated.apexcharts.config.chart.builder.ZoomBuilder;
import com.github.appreciated.apexcharts.config.xaxis.XAxisType;
import com.github.appreciated.apexcharts.helper.Series;
import com.vaadin.flow.component.html.Div;

public class BubbleChartExample extends Div {
    public BubbleChartExample() {
        ApexCharts bubbleChart = new ApexCharts()
                .withChart(
                        ChartBuilder.get()
                                .withType(Type.bubble)
                                .withZoom(ZoomBuilder.get()
                                        .withEnabled(false)
                                        .build())
                                .build())
                .withDataLabels(DataLabelsBuilder.get()
                        .withEnabled(false)
                        .build())
                .withFill(FillBuilder.get().withOpacity(0.8).build())
                .withTitle(TitleSubtitleBuilder.get().withText("Desembolvimiento de estudiantes por grado").build())
                .withSeries(new Series("1ero de secundaria", new Double[]{637.0, 52.0, 46.0}, // {X, Y, <Bubble Size>}
                                new Double[]{162.0, 59.0, 33.0},
                                new Double[]{400.0, 52.0, 60.0},
                                new Double[]{561.0, 54.0, 39.0},
                                new Double[]{731.0, 27.0, 67.0},
                                new Double[]{697.0, 60.0, 33.0}),
                        new Series("2do de secundaria", new Double[]{73.0, 32.0, 74.0},
                                new Double[]{459.0, 31.0, 65.0},
                                new Double[]{386.0, 44.0, 60.0},
                                new Double[]{671.0, 28.0, 53.0},
                                new Double[]{125.0, 33.0, 50.0},
                                new Double[]{745.0, 45.0, 28.0}),
                        new Series("1ero y 2do", new Double[]{502.0, 60.0, 57.0},
                                new Double[]{121.0, 58.0, 51.0},
                                new Double[]{108.0, 42.0, 55.0},
                                new Double[]{556.0, 35.0, 62.0},
                                new Double[]{331.0, 36.0, 53.0},
                                new Double[]{251.0, 49.0, 25.0}))
                .withXaxis(XAxisBuilder.get().withType(XAxisType.numeric).build())
                .withYaxis(YAxisBuilder.get().withMax(70.0).build());
        add(bubbleChart);
        setWidth("350px");
    }
}
