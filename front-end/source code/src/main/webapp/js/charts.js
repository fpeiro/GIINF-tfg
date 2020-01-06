zingchart.MODULESDIR = "https://cdn.zingchart.com/modules/";
ZC.LICENSE = ["569d52cefae586f634c54f86dc99e6a9", "ee6b7db5b51705a13dc2339db3edaf6d"];

chartvalues = JSON.parse(localStorage.getItem('chartvalues'));
if (!chartvalues) {
    chartvalues = [];
}

function getRandomColor() {
    var letters = '0123456789ABCDEF';
    var color = '#';
    for (var i = 0; i < 6; i++) {
        color += letters[Math.floor(Math.random() * 16)];
    }
    return color;
}

var myConfig = {
    type: 'radar',
    plot: {
        aspect: 'area',
        animation: {
            effect: 3,
            sequence: 1,
            speed: 700
        }
    },
    plotarea: {//main chart area
        margin: '20px 0px 35px 0px' //accepts 5, 5px, 5%
    },
    scaleV: {
        visible: false
    },
    scaleK: {
        labels: JSON.parse(localStorage.getItem('chartnames')),
        item: {
            fontColor: '#607D8B',
            backgroundColor: "white",
            borderColor: "#aeaeae",
            borderWidth: 1,
            padding: '5 10',
            borderRadius: 10
        },
        refLine: {
            lineColor: '#c10000'
        },
        tick: {
            lineColor: '#59869c',
            lineWidth: 2,
            lineStyle: 'dotted',
            size: 20
        },
        guide: {
            lineColor: "#607D8B",
            lineStyle: 'solid',
            alpha: 0.3,
            backgroundColor: "#c5c5c5 #718eb4"
        }
    },
    series: [
        {
            values: chartvalues,
            lineColor: getRandomColor(),
            backgroundColor: getRandomColor()
        }
    ]
};

zingchart.render({
    id: 'myChart',
    data: myConfig,
    height: '100%',
    width: '100%'
});