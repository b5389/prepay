import React from 'react'
import { NavBar,List,Form,TextArea, Button,Dialog,Toast } from 'antd-mobile'
import { useNavigate } from 'react-router-dom'

export default function CheckOut() {
    const nav=useNavigate()
    const back=()=>{
        nav('/tabs/Cards')
    }
    const onFinish= async ()=>{
        const result = await Dialog.confirm({
            content: '是否确认退卡?',
          })
          if (result) {
            Toast.show({ content: '已提交申请，等待商家确认', position: 'bottom' })
            back()
          } else {
            Toast.show({ content: '退卡失败，原因：...', position: 'bottom' })
          }

    }
  return (
    <div>
      <NavBar onBack={back}>退卡页面</NavBar>
      <List header='卡片信息' mode='card'>
        <List.Item extra='xxx'>持卡人姓名</List.Item>
        <List.Item extra='商家一'>商家姓名</List.Item>
        <List.Item extra='2022年12月1日'>卡到期时间</List.Item>
        <List.Item extra='200元'>卡余额</List.Item>
      </List>
      <Form footer={
        <Button block type='submit' color='warning' size='large'>退卡</Button>
      }
      onFinish={onFinish}>
        <Form.Header>退卡理由</Form.Header>
        <Form.Item name='reason' >
            <TextArea 
            placeholder='请留下您的宝贵意见'
            autoSize={{minRows:3,maxRows:5}}/>
        </Form.Item>
      </Form>
    </div>
  )
}
