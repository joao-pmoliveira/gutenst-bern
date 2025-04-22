import Link from "next/link";
import SearchBar from "./SearchBar";

export default async function Header() {

    return (
        <header className="bg-slate-200">
            <nav className="mx-auto flex max-w-7xl items-center justify-between px-6 py-2" aria-label="Global">
                <div className="basis-1/3">
                    <Link href="/" className="-m-1.5 p-1.5"> Project Gutenstobern </Link>

                </div>
                <SearchBar />

                <div className="basis-1/3 flex justify-end">
                    <Link href="/user"
                        className="text-sm/6 font-semibold text-gray-900" >
                        User
                    </Link>
                </div>
            </nav>
        </header>
    )
}
