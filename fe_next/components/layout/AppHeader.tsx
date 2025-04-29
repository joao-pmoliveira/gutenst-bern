import { LibraryBig } from "lucide-react";
import Link from "next/link";
import { Button } from "../ui/button";
import SearchBar from "../pgui/SearchBar";

export default function AppHeader() {
    return (
        <header className="bg-equator">
            <nav className="max-w-7xl mx-auto py-2
                flex justify-between gap-10
            ">
                <Link href={"/"} className="flex items-center text-2xl">
                    <LibraryBig size={28} color="black" strokeWidth={1.25} />
                    Gutenstöbern
                </Link>

                <SearchBar />

                <ul className="flex items-center gap-8">
                    <li>
                        <Link href={"#"} className="text-lg">Browse</Link>
                    </li>
                    <li>
                        <Link href={"#"} className="text-lg">My Books</Link>
                    </li>
                    <li>
                        <Button className="bg-bottle-green">
                            <Link href={"/login"}>Login</Link>
                        </Button>
                    </li>
                </ul>
            </nav>
        </header>
    )
}
