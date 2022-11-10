import React from 'react'
import { Card,Button } from 'antd-mobile'
import './demo2.less'
import { useNavigate } from 'react-router-dom'

export default function CardItem(props) {
  const navigate=useNavigate()
  const card2Exp=()=>{
    navigate('/expRec')
  }
  const card2CheckOut=()=>{
    navigate('/checkOut')
  }
  const card2Contract=()=>{
    navigate('/showContract')
  }
  return (
    <div>
      <Card title={
        <div style={{fontWeight:'bold',fontSize:'x-large'}}>
          {props.name}
        </div>
      }
      className='card'
      >
        <div>
          卡片余额:100元
        </div>
        {/* 不知道为什么这个消费到期时间的div渲染不出来 */}
        {/* <div className="content"><span>预计消费到期时间:{props.date}</span></div>
        <div className="content"><span>预计消费到期时间:{props.date}</span></div> */}
        <div className='footer'>
        <Button color='success'style={{right:'10px'}} onClick={card2Contract}>
            查看合同
          </Button>
          <Button color='primary'style={{right:'5px'}} onClick={card2Exp}>
            查看消费记录
          </Button>
          <Button color='warning'onClick={card2CheckOut}>
            申请退卡
          </Button>
        </div>
      </Card>
    </div>
  )
}
