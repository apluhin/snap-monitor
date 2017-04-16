function reviewData(data1) {
    var arr1 = [['Router', 'Load']];
    for (var i = 0; i < data1.length; i++) {
        var date = new Date(data1[i].timestamp);
        var str = date.getMinutes();
        if (date.getMinutes() < 10) {
            str = "0" + str;
        }
        data1[i].timestamp = date.getHours() + ":" + str;
        arr1[i + 1] = [data1[i].timestamp, data1[i].load]
    }
    if (data1.length == 0) {
        arr1[1] = ["00:00", 0];
    }
    return arr1;
}
function convert(str) {
    str = str + ""
    if (str.length == 1) {
        return "0" + str;
    } else {
        return str;
    }
}
var Main = React.createClass({


    getInitialState: function () {

        var to = new Date();
        to.setTime(to.getTime() + 360000)
        var to1 = to.getUTCFullYear() + "-" + convert(to.getMonth() + 1) + "-" + convert(to.getDate()) + " " + convert(to.getHours()) + ":" + convert(to.getMinutes()) + ":00";
        var a = to.getTime();
        a = a - 3600000;
        to.setTime(a)


        var from1 = to.getUTCFullYear() + "-" + convert(to.getMonth() + 1) + "-" + convert(to.getDate()) + " " + convert(to.getHours()) + ":" + convert(to.getMinutes()) + ":00";
        var Data = {
            array: [["Router", "Load"], ["00:00", 0]],
            timeTo: to1,
            timeFrom: from1
        }
        return {data: Data}
    },


    componentDidMount: function () {
        this.send();
    },

    componentDidUpdate: function (prevProps, prevState) {
        if (this.state.data.timeFrom.length == 19 && this.state.data.timeTo.length == 19) {
            this.send();
        }
    },

    generateView: function () {
        var a = this.props.device.ipAddress;
        $.get("/device?action=cpu&address=" + a.replace("/", "")).done(function (data1) {
            var arr1 = reviewData(data1);
            generateTable(arr1)
        });

    },

    setFrom: function (event) {
        console.log(event.target.value)
        this.state.data.timeFrom = event.target.value;
        var Data = {
            array: this.state.data.array,
            timeTo: this.state.data.timeTo,
            timeFrom: event.target.value
        }
        this.setState({data: Data});
    },

    setTo: function (event) {
        var Data = {
            array: this.state.data.array,
            timeTo: event.target.value,
            timeFrom: this.state.data.timeFrom
        }
        this.setState({data: Data});
    },

    send: function () {
        $.get('/device?action=cpu_interval&address=' + this.props.device.ipAddress.replace("/", "") + '&from=' + this.state.data.timeFrom + "&to=" + this.state.data.timeTo).done(function (data) {
            generateTable(reviewData(data))
        }.bind(this));
    },

    render: function () {

        var str = this.props.device.vendor.toLowerCase();
        if (this.state.typeRender == 'nothing') {
            return <div></div>
        }
        return (<div id="device-panel">
            <div id="main-panel">

                Имя устройства: <b>{this.props.device.name}</b><br></br>
                IP адрес устройства: <b>{this.props.device.ipAddress}</b><br></br>
                Вендор <b>{str}</b><br></br>
                <b>Выбор времени</b><br></br>
                Загрузка с <input onChange={this.setFrom} value={this.state.data.timeFrom} type="text"/> до <input
                onChange={this.setTo} value={this.state.data.timeTo} type="text"/><br></br>
                <b onClick={this.send}>Отправить</b><br></br>
            </div>
        </div>);
    }

});


function generateTable(data) {
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
                <a className="navbar-brand">Monitoring</a>
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
        $("#container1").removeClass("non-visible");
        $("#placeholder").removeClass("non-visible");
        ReactDOM.render(
            <Main device={el}/>,
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

                    if (el.online == false) {
                        return <li className="list-group-item" style={{'background': 'red'}} key={index}
                                   onClick={() => console.handlerMain(el)}>{el.ipAddress}
                        </li>
                    } else {
                        return <li className="list-group-item" key={index}
                                   onClick={() => console.handlerMain(el)}>{el.ipAddress}
                        </li>
                    }


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
        $("#container").removeClass("non-visible");
        $("#container1").addClass("non-visible")
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
        $("#container1").removeClass("non-visible");
        $("#container").addClass("non-visible");
        ReactDOM.render(
            <Device/>,
            document.getElementById('container1')
        );

    },

    render: function () {
        return ( <li onClick={this.handlerMain}><a>Devices</a></li>)
    }
});

var Device = React.createClass({

    getInitialState: function () {
        $("#container").addClass("non-visible");

        return {File: null}
    },

    render: function () {
        return <div>
            <p>
                <input type="file" onChange={this.setFile}/>
            </p>
            <p>
                <input type="button" onClick={this.send}/>
            </p>
        </div>


    },

    setFile: function (event) {
        this.setState({File: event.target.files[0]})
        console.log()

    },

    send: function () {
        console.log(this.state.File)
        var formData = new FormData();
        formData.append('file', this.state.File)
        $.ajax({
            type: 'POST',
            url: '/device?action=add',
            data: formData,
            processData: false,
            contentType: false,
            dataType: "json",
            success: function (data) {
                console.log(data);
            },
            error: function (data) {
                console.log(data);
            }
        });
    }


});

ReactDOM.render(
    <Header/>,
    document.getElementById('header')
);
