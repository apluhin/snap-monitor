var Main = React.createClass({


    getInitialState: function () {
        return {array: [["Router", "Load"], ["00:00", 0]]}
    },

    re: function () {
        var a = this.props.address;
        $.get("/device?action=cpu&address=" + a.replace("/", "")).done(function (data1) {
            var arr1 = [['Router', 'Load']];
            for (var i = 0; i < data1.length; i++) {
                var date = new Date(data1[i].timestamp);
                data1[i].timestamp = date.getHours() + ":" + date.getMinutes();
                arr1[i + 1] = [data1[i].timestamp, data1[i].load]
            }
            test(arr1)
        });

    },


    render: function () {
        this.re();
        if (this.state.typeRender == 'nothing') {
            return <div></div>
        }
        return (<h onClick={this.re}>Load</h>);
    }

});


function test(data) {
    google.charts.load('current', {'packages': ['corechart']});
    google.charts.setOnLoadCallback(drawChart);

    function drawChart() {

        var d = google.visualization.arrayToDataTable(data);

        var options = {
            title: 'Load',
            hAxis: {title: 'Time', titleTextStyle: {color: '#333'}},
            vAxis: {minValue: 0}
        };

        var chart = new google.visualization.AreaChart(document.getElementById('placeholder'));
        chart.draw(d, options);
    }
}

var Header = React.createClass({


    render: function () {
        return (
            <div className="navbar navbar-default" id="header">
                <a className="navbar-brand">Translate</a>
                <ul style={{cursor: "pointer"}} className="nav navbar-nav" id="menu">
                    <MainLink/>
                    <SomeLink/>
                </ul>
            </div>
        );
    }
});

var MainListOfDevice = React.createClass({

    getInitialState: function () {
        $("#container").html("");
        $("#container1").html("");
        return null;
    },

    handlerMain: function (el) {
        $("#container").removeClass("non-visible");
        $("#placeholder").removeClass("non-visible");
        ReactDOM.render(
            <Main address={el}/>,
            document.getElementById('container1')
        )
        ;

    },

    getInitialState: function () {
        return {listDevices: []}
    },

    componentDidMount: function () {
        $.get('/device?action=all').done(function (data) {
            this.setState({listDevices: data});
        }.bind(this));
    },

    render: function () {
        var console = this;
        return <ul id="list-texts" className="list-group">
            {

                this.state.listDevices.map(function (el, index) {
                    return <li key={index} onClick={() => console.handlerMain(el.ipAddress)}>{el.ipAddress}</li>;
                })
            }
        </ul>
    }


});


var MainLink = React.createClass({

    getInitialState: function () {
        $("#container").html("");
        return null;
    },

    handlerMain: function () {
        ReactDOM.render(
            <MainListOfDevice/>,
            document.getElementById('container')
        );

    },

    render: function () {
        return ( <li onClick={this.handlerMain}><a>Monitoring</a></li>)
    }
});

var SomeLink = React.createClass({

    getInitialState: function () {
        $("#container").html("");
        $("#container1").html("");
        $("#placeholder").html("");
        return null;
    },


    handlerMain: function () {
        $("#placeholder").html("");
        ReactDOM.render(
            <Nothing/>,
            document.getElementById('container')
        );

    },

    render: function () {
        return ( <li onClick={this.handlerMain}><a>Monitoring1</a></li>)
    }
});

var Nothing = React.createClass({
    render: function () {
        return <div></div>
    }
});

ReactDOM.render(
    <Header/>,
    document.getElementById('header')
);
