{
title: {
text: ${text},
left: '100',
top: '100'
},
legend: {
top: '47%',
left: '45%',
orient: 'vertical',
data: [  ${industry}, ${oneself}]

},
series: [
{
name: '行业的圈',
type: 'pie',
selectedMode: 'single',
radius: ['40%', '45%'],
label: {
position: 'center',
fontSize: 14,
},

data: [
{value: ${ivalue},name: ${industry},itemStyle: {
color: '#3cb371'
}},
{value: ${ivalueOther},itemStyle: {
color: '#e6e6fa'
}},
]
},
{
name: '受评人的圈',
type: 'pie',
radius: ['45%', '50%'],
label: {
position: 'center',
fontSize: 14,
},

data: [
{value: ${ovalue}, name: ${oneself},
itemStyle: {
color: '#4169e1'
}},
{value: ${ovalueOther}, itemStyle: {
color: '#e6e6fa'
}}
]
}
]
}