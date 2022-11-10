import React from 'react'
import CardItem from './CardItem'

export default function index() {
  const cards=[
    {
      id:1,
      name:'商家一',
      deadDate:'2022年12月1日'
    },
    {
      id:2,
      name:'商家二',
      deadDate:'2023年6月1日'
    },
    {
      id:3,
      name:'商家三',
      deadDate:'2023年4月5日'
    }
  ]
  return (
    <div style={{top:'-0px',position:'relative'}}>
      {
        cards.map(item =>(
          <CardItem key={item.id} name={item.name} date={item.deadDate}/>
        ))
      }
    </div>
  )
}
