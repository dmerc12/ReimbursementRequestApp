import Link from 'next/link'

export default function Home({ user }) {
  return (
    <>
      <Link href='/manage-requests'>Manage Request Information</Link>
      <Link href='/manage-information'>Manage Employee Information</Link>
    </>
  )
}
