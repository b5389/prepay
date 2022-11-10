import React, { FC } from 'react'
import { NavBar, TabBar } from 'antd-mobile'
import {
  Route,
  Routes,
  useNavigate,
  useLocation,
  MemoryRouter as Router,
  Outlet,
  useRoutes
} from 'react-router-dom'
import {
  AppOutline,
  MessageOutline,
  UnorderedListOutline,
  UserOutline,
} from 'antd-mobile-icons'
import routes from "../../routes"

import './demo2.less'

const Bottom: FC = () => {
  const navigate = useNavigate()
  const location = useLocation()
  const { pathname } = location

  const setRouteActive = (value: string) => {
    navigate(value)
  }

  const tabs = [
    {
      key: 'Shops',
      title: '商家',
      icon: <AppOutline />,
    },
    {
      key: 'Cards',
      title: '卡包',
      icon: <UnorderedListOutline />,
    },
    {
      key: 'messages',
      title: '消息',
      icon: <MessageOutline />,
    },
    {
      key: 'me',
      title: '我的',
      icon: <UserOutline />,
    },
  ]

  return (
    <TabBar activeKey={pathname} onChange={value => setRouteActive(value)}>
      {tabs.map(item => (
        <TabBar.Item key={item.key} icon={item.icon} title={item.title} />
      ))}
    </TabBar>
  )
}

export default function Tabs() {
  const element=useRoutes(routes)
  return (
    // <Router initialEntries={['/home']}>
      <div className="app">
        <div className="top">
          <NavBar backArrow={false}>PrePay</NavBar>
        </div>
        <div className="body">
          {element}
          <Outlet/>
        </div>
        <div className="bottom">
          <Bottom />
        </div>
      </div>
    // {/* </Router> */}
  )
}
