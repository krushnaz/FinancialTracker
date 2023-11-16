'use strict';

/* Chart.js docs: https://www.chartjs.org/ */

window.chartColors = {
	green: '#75c181', // rgba(117,193,129, 1)
	blue: '#5b99ea', // rgba(91,153,234, 1)
	gray: '#a9b5c9',
	text: '#252930',
	border: '#e7e9ed',
	yellow: '#FFCD4B',
	red: '#D80032'
};

/* Random number generator for demo purpose */
var randomDataPoint = function(){ return Math.round(Math.random()*100)};

// Doughnut Chart Demo

var doughnutChartConfig = {
	type: 'doughnut',
	data: {
		datasets: [{
			data: [
				randomDataPoint(),
				randomDataPoint(),
				randomDataPoint(),
				randomDataPoint(),
			],
			backgroundColor: [
				window.chartColors.green,//Income
				window.chartColors.yellow,//Loan
				window.chartColors.red,//Expense
				window.chartColors.grey,//Investment

			],
			label: 'Dataset 1'
		}],
		labels: [
			'Income',
			'Loan',
			'Expense',
			'Investment',
		]
	},
	options: {
		responsive: true,
		legend: {
			display: true,
			position: 'bottom',
			align: 'center',
		},

		tooltips: {
			titleMarginBottom: 10,
			bodySpacing: 10,
			xPadding: 16,
			yPadding: 16,
			borderColor: window.chartColors.border,
			borderWidth: 1,
			backgroundColor: '#fff',
			bodyFontColor: window.chartColors.text,
			titleFontColor: window.chartColors.text,
			
			animation: {
				animateScale: true,
				animateRotate: true
			},
			
			/* Display % in tooltip - https://stackoverflow.com/questions/37257034/chart-js-2-0-doughnut-tooltip-percentages */
			callbacks: {
                label: function(tooltipItem, data) {
					//get the concerned dataset
					var dataset = data.datasets[tooltipItem.datasetIndex];
					//calculate the total of this data set
					var total = dataset.data.reduce(function(previousValue, currentValue, currentIndex, array) {
					return previousValue + currentValue;
					});
					//get the current items value
					var currentValue = dataset.data[tooltipItem.index];
					//calculate the precentage based on the total and current item, also this does a rough rounding to give a whole number
					var percentage = Math.floor(((currentValue/total) * 100)+0.5);
					
					return percentage + "%";
			    },
            },
			

		},
	}
};



// Generate charts on load
window.addEventListener('load', function(){
	
	var doughnutChart = document.getElementById('chart-doughnut').getContext('2d');
	window.myDoughnut = new Chart(doughnutChart, doughnutChartConfig);
	

});	