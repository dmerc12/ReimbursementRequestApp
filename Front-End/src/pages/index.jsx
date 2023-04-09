import { getSession } from 'next-auth/react'
import Link from 'next/link'

export default function Home({ user }) {
  return (
    <>
      <Link href='/manage-requests'>Manage Request Information</Link>
      <Link href='/manage-information'>Manage Employee Information</Link>
    </>
  )
}

export async function getServerSideProps(context) {
  const session = await getSession(context)

  if (!session) {
    return {
      redirect: {
        destination: 'login',
        permanent: false
      }
    }
  }

  return {
    props: {
      user: session.user
    }
  }
}