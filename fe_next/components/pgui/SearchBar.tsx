import { Search } from "lucide-react";

export default function SearchBar() {
    return (
        <div className="max-w-md flex items-center
            bg-gallery
            rounded-full px-3 grow">

            <Search size={20} color="black" strokeWidth={2} />

            <input type="text" placeholder="Search Book"
                id="search" name="search"
                className="grow pl-3
                placeholder:text-white-400 focus:outline-none" />
        </div>
    )
}
