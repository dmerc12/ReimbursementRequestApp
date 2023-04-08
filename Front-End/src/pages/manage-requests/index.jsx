import { getSession } from 'next-auth/react'


export default function ManageRequests({ user }) {
    return (
        <>
            <h1>Manage Requests Page</h1>
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
